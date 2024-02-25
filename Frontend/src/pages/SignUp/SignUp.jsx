// import React from 'react'
import { Link } from 'react-router-dom'
import { useForm } from 'react-hook-form'

const SignUp = () => {

    const { handleSubmit, register, formState: { errors }, reset } = useForm()

    const onSubmit = (data) => {
        console.log(data)
        reset()
	}

    return (
        <section className='px-6 h-screen bg-[#191919] flex justify-center items-center lg:px-0'>
            <div className='hidden h-full lg:block w-[75%]'>
               {/* <img className='w-full' src="" alt="Logo de la app" /> */}
                <div className='h-full w-full bg-blue-300'></div>
            </div>
            <div className='w-full flex justify-center items-center md:w-[50%]'>
                <form method='post' onSubmit={ handleSubmit(onSubmit) } className='text-center w-full lg:w-[60%]'>
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
                                minLength: { value: 3, message: 'El nombre debe tener entre 3 y 25 caracteres' },
                                maxLength: { value: 25, message: 'El nombre no debe superar los 25 caracteres' },
                            })}
                        />
                        <span className='text-xs text-start w-full text-red-500 pb-3 xl:text-sm'>{errors.name && errors.name.message}</span>
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
                            })}
                        />
                        <span className='text-xs text-start w-full text-red-500 pb-3 xl:text-sm'>{errors.email && errors.email.message}</span>
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
                        <span className='text-xs text-start w-full text-red-500 pb-3 xl:text-sm'>{errors.password && errors.password.message}</span>
                        <div className=' pt-3 space-y-7 lg:w-full'>
                            <input className=' bg-[#F5F5F5] rounded-lg text-black w-full py-2 font-medium text-sm' type="submit" value="Crear cuenta" />
                            <p className='text-xs text-white'>¿Ya tienes una cuenta? <Link to='/login' className=' text-blue-500'>Inicia sesión</Link></p>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    )
}

export default SignUp