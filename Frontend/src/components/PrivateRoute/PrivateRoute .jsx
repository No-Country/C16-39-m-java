import React, { useContext } from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext/AuthContext';

const PrivateRoute = () => {

    const { isLoggedIn } = useContext(AuthContext)

    if (!isLoggedIn) {
        return <Navigate to='/' />
    }

    return (
        <Outlet />
    )
}

export default PrivateRoute
