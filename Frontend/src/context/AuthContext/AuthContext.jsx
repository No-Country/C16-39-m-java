import { createContext, useState } from "react";

export const AuthContext = createContext()

export const AuthContextProvider = ({ children }) => {

    const [isLoggedIn, setIsLoggedIn] = useState(false)
    const [userData, setUserData] = useState([])

    const login = (userData) => {
        setIsLoggedIn(true)
        setUserData(userData)
    }

    const logout = () => {
        setIsLoggedIn(false)
        setUserData([])
    }

    return (
        <AuthContext.Provider value={{ isLoggedIn, userData, login, logout }}>
            { children }
        </AuthContext.Provider>
    )
}