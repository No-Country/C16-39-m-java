import React, { useState } from 'react'
import { GoHome } from "react-icons/go";
import { MdFavoriteBorder } from "react-icons/md";
import { BiCameraMovie } from "react-icons/bi";
import { TbLogout } from "react-icons/tb";
import { NavLink } from 'react-router-dom'
import img from './assets/logo.png'


const Menu = () => {

    return (
        <>
            <aside className='hidden h-screen fixed top-0 w-44 rounded-br-3xl rounded-tr-3xl bg-[#212121] text-[#b9b9b9]  lg:block'>
                <div className='flex flex-col items-center w-full justify-between'>
                    <img src={img} alt="logo de la app" />
                    <div className='w-full mt-5 space-y-12 font-medium'>
                        <NavLink to='/' className=' flex items-center pl-8'>
                            <GoHome className=' text-xl mr-3' />
                            <small className=' text-[.7rem]'>Inicio</small>
                        </NavLink>
                        <NavLink to='/top-ranking' className=' flex items-center pl-8'>
                            <BiCameraMovie className=' text-xl mr-3' />
                            <small className=' text-[.7rem]'>Top ranking</small>
                        </NavLink>
                        <NavLink to='/favorite' className=' flex items-center pl-8'>
                            <MdFavoriteBorder className=' text-xl mr-3' />
                            <small className=' text-[.7rem]'>Favoritos</small>
                        </NavLink>
                    </div>
                    <div className='absolute bottom-10 font-medium w-full'>
                        <NavLink to='/login' className='pl-8 py-3 flex items-center bg-[#2C6865]'>
                            <TbLogout className=' text-xl mr-3' />
                            <small className=' text-[.7rem]'>Cerrar sesión</small>
                        </NavLink>
                    </div>
                </div>
            </aside>

            {/* Menu Mobile */}
            <aside className='fixed bottom-0 h-14 w-full rounded-t-lg py-3 bg-[#212121] text-[#b9b9b9] flex justify-evenly items-center font-medium lg:hidden'>
                <NavLink className='flex flex-col items-center'>
                    <TbLogout className=' text-xl' />
                    <small className=' text-[.6rem]'>Cerrar sesión</small>
                </NavLink>
                <NavLink to='/top-ranking' className='flex flex-col items-center'>
                    <BiCameraMovie className=' text-xl' />
                    <small className=' text-[.6rem]'>Top ranking</small>
                </NavLink>
                <NavLink to='/favorite' className=' flex flex-col items-center'>
                    <MdFavoriteBorder className=' text-xl' />
                    <small className=' text-[.63rem]'>Favoritos</small>
                </NavLink>
                <NavLink to='/' className=' flex flex-col items-center'>
                    <GoHome className=' text-xl' />
                    <small className=' text-[.63rem]'>Inicio</small>
                </NavLink>
            </aside>
        </>

    )
}

export default Menu