import React, { useContext } from 'react'
import { GoHome } from "react-icons/go";
import { MdFavoriteBorder } from "react-icons/md";
import { BiCameraMovie } from "react-icons/bi";
import { TbLogout } from "react-icons/tb";
import { NavLink } from 'react-router-dom'
import img from './assets/logo-moviepulse.png'
import { AuthContext } from '../../context/AuthContext/AuthContext';


const Menu = () => {

    const { logout } = useContext(AuthContext)

    return (
        <>
            <aside className='hidden h-screen fixed top-0 w-44 xl:w-48 rounded-br-3xl rounded-tr-3xl bg-[#212121] text-[#b9b9b9]  lg:block'>
                <div className='flex flex-col items-center w-full justify-between'>
                    <img className='w-44' src={img} alt="logo de la app" />
                    <div className='w-full mt-5 space-y-6 font-medium'>
                        <NavLink to='/dashboard' className=' flex items-center pl-8 py-4 transition-all hover:bg-[#fab221f1] hover:text-white'>
                            <GoHome className=' text-xl mr-3' />
                            <small className=' text-[.8rem]'>Inicio</small>
                        </NavLink>
                        <NavLink to='/top-ranking' className=' flex items-center pl-8 py-4 transition-all hover:bg-[#fab221f1] hover:text-white'>
                            <BiCameraMovie className=' text-xl mr-3' />
                            <small className=' text-[.8rem]'>Top Ranking</small>
                        </NavLink>
                        <NavLink to='/favorite' className=' flex items-center pl-8 py-4 transition-all hover:bg-[#fab221f1] hover:text-white'>
                            <MdFavoriteBorder className=' text-xl mr-3' />
                            <small className=' text-[.8rem]'>Favoritos</small>
                        </NavLink>
                    </div>
                    <div className='absolute bottom-10 font-medium w-full'>
                        <NavLink onClick={logout} to='/' className='pl-8 py-3 flex items-center transition-all hover:bg-red-500 hover:text-white'>
                            <TbLogout className=' text-xl mr-3' />
                            <small className=' text-[.8rem]'>Cerrar sesión</small>
                        </NavLink>
                    </div>
                </div>
            </aside>
            {/* Menu Mobile */}
            <aside className='fixed bottom-0 h-14 w-full rounded-t-lg py-3 bg-[#212121] text-[#b9b9b9] flex justify-around items-center font-medium z-50 lg:hidden'>
                <dialog id="my_modal_1" className={`modal rounded-lg bg-[#20201f60] px-6 py-5`}>
                    <div className="modal-box bg-[#444444]">
                        <h3 className="font-medium text-lg text-[#fff]">Cerrar sesión</h3>
                        <p className="py-3 text-[#fff] text-sm">¿Seguro que quieres cerrar sesión?</p>
                        <div className=' pt-3 flex justify-end space-x-5 text-xs text-white'>
                            <button onClick={() => document.getElementById('my_modal_1').close()} className='hover:underline'>Cancelar</button>
                            <NavLink onClick={logout} to='/' className='bg-red-500 p-2 rounded-md'>Cerrar sesión</NavLink>
                        </div>
                    </div>
                </dialog>
                <NavLink onClick={() => document.getElementById('my_modal_1').showModal()} className='flex flex-col items-center'>
                    <TbLogout className=' text-[1.3rem]' />
                    <small className=' text-[.63rem]'>Cerrar sesión</small>
                </NavLink>
                <NavLink to='/top-ranking' className='flex flex-col items-center -ml-3'>
                    <BiCameraMovie className='text-[1.3rem]' />
                    <small className=' text-[.63rem]'>Top Ranking</small>
                </NavLink>
                <NavLink to='/favorite' className=' flex flex-col items-center mr-2.5'>
                    <MdFavoriteBorder className=' text-[1.3rem]' />
                    <small className=' text-[.63rem]'>Favoritos</small>
                </NavLink>
                <NavLink to='/dashboard' className=' flex flex-col items-center  mr-2'>
                    <GoHome className=' text-[1.35rem]' />
                    <small className=' text-[.67rem] pt-.5'>Inicio</small>
                </NavLink>
            </aside>
        </>

    )
}

export default Menu