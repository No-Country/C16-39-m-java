import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { useForm } from 'react-hook-form'
import img from './assets/background-signup.png'
import logo from './assets/logo-moviepulse.png'
import axios from 'axios'

const SignUp = () => {

    const navigate = useNavigate()

    const [toast, setToast] = useState(false)
    const [messageToast, setMessageToast] = useState('')
    const [success, setSuccess] = useState(false)
    const [loading, setLoading] = useState(false)

    const backgroundImage = `url(${img})`

    const { handleSubmit, register, formState: { errors }, reset } = useForm()

    const onSubmit = async (data) => {
        try {
            setLoading(true)
            const result = await axios.post('https://movies-apirest-c77e9f5e2ba2.herokuapp.com/users', data)
            const responseData = result.data

            const successMessage = responseData.active ? 'Registro Exitoso' : 'Ha ocurrido un error al registrar tu cuenta. Por favor, intenta nuevamente.'

            setSuccess(responseData.active)
            setMessageToast(successMessage)
            setToast(true)
            setTimeout(() => {
                setLoading(false)
                setToast(false)
                if (responseData.active) {
                    navigate('/')
                }
            }, 2500)
        } catch (error) {
            setLoading(false)
            setToast(true)
            setSuccess(false)
            setMessageToast('Error en el servidor. Por favor, intenta más tarde.')
            setTimeout(() => {
                setToast(false)
            }, 3000)
        }

        reset()
    }

    return (
        <section className='px-4 h-screen bg-[#191919] flex justify-center items-center md:px-0'>
            <div className={`absolute mx-3 text-center py-2 px-4 rounded-md z-30 transition-all ${toast ? 'top-3' : '-top-full'} ${success ? 'bg-green-500' : 'bg-red-500'}`}>
                <span className='text-white text-sm font-medium lg:text-base'>{messageToast}</span>
            </div>
            <div style={{ backgroundImage }} className='relative hidden h-full bg-center bg-cover md:block w-[75%]'>
                <div className='absolute inset-0 bg-[#000000]/75'></div>
                <img className='absolute w-32' src={logo} alt="Logo de la app" />
                <div className='absolute left-8 bottom-32 text-white space-y-3 z-20 xl:bottom-36'>
                    <h3 className='font-medium text-xl xl:text-2xl'>Beneficios de tu cuenta gratuita</h3>
                    <div>
                        <p className='text-sm font-medium xl:text-base'>Descubre más de 1000 películas.</p>
                        <small>Puedes ver toda la información acerca de las películas.</small>
                    </div>
                    <div>
                        <p className='text-sm font-medium xl:text-base'>Tus favoritos.</p>
                        <small>Guarda tus películas favoritas.</small>
                    </div>
                </div>
            </div>
            <div className='w-full px-4 flex justify-center items-center md:w-[50%] lg:px-0'>
                <form method='post' onSubmit={handleSubmit(onSubmit)} className='text-center mt-8 w-full lg:w-[60%]'>
                    <h2 className='text-[#FEFEFE] text-xl mb-8'>Crea una cuenta</h2>
                    <div className=' flex flex-col items-center space-y-2 '>
                        <label htmlFor="name"></label>
                        <input
                            className='input-signup'
                            placeholder='Nombre'
                            autoComplete='off'
                            type="text"
                            name="name"
                            id="name"
                            {...register('name', {
                                required: 'Este campo es obligatorio',
                                pattern: {
                                    value: /^[a-zA-Z\s]+$/,
                                    message: 'El nombre no debe tener números ni caracteres especiales.',
                                },
                                minLength: { value: 3, message: 'El nombre debe tener entre 3 y 25 caracteres' },
                                maxLength: { value: 25, message: 'El nombre no debe superar los 25 caracteres' },
                            })}
                        />
                        <span className='text-xs text-start w-full text-red-600 pb-3 xl:text-sm'>{errors.name && errors.name.message}</span>
                        <label htmlFor="email"></label>
                        <input
                            className='input-signup'
                            placeholder='Dirección de correo electrónico'
                            autoComplete='off'
                            type="email"
                            name="email"
                            id="email"
                            {...register('email', {
                                required: 'Este campo es obligatorio',
                                pattern: {
                                    value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i,
                                    message: 'Correo electrónico inválido',
                                },
                                maxLength: { value: 50, message: 'El email no debe superar los 50 caracteres' },
                            })}
                        />
                        <span className='text-xs text-start w-full text-red-600 pb-3 xl:text-sm'>{errors.email && errors.email.message}</span>
                        <label htmlFor="password"></label>
                        <input
                            className='input-signup'
                            placeholder='Contraseña'
                            autoComplete='off'
                            type="password"
                            name="password"
                            id="password"
                            {...register('password', {
                                required: 'Este campo es obligatorio',
                                minLength: { value: 8, message: 'La contraseña debe tener entre 8 y 25 caracteres' },
                                maxLength: { value: 25, message: 'La contraseña no debe superar los 25 caracteres' },
                            })}
                        />
                        <span className='text-xs text-start w-full text-red-600 pb-3 xl:text-sm'>{errors.password && errors.password.message}</span>
                        <div className=' pt-3 space-y-7 lg:w-full'>
                            <button type='submit' className=' flex justify-center items-center cursor-pointer rounded-lg text-black bg-[#F5F5F5] py-2 font-medium text-sm w-full'>Crear cuenta
                                {
                                    loading && <span className="loading loading-spinner loading-sm ml-4"></span>
                                }
                            </button>
                            <p className='text-xs text-white'>¿Ya tienes una cuenta? <Link to='/' className=' text-blue-500 hover:underline'>Inicia sesión</Link></p>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    )
}

export default SignUp