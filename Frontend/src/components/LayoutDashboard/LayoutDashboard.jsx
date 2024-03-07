import React from 'react'
import Menu from '../Menu/Menu'

const LayoutDashboard = ({ children }) => {

    return (
        <section>
            <Menu />
            { children }
        </section>
    )
}

export default LayoutDashboard