import {Routes, Route} from "react-router-dom"
import Login from "./pages/Login/Login"
import SignUp from "./pages/SignUp/SignUp";
import Home from "./pages/Home/Home";
import DetallesPeliculas from "./pages/Detalle/DetallesPeliculas"
import PaginaNoEncontrada from "./pages/PaginaNoEncontrada/PaginaNoEncontrada"


function App() {
	
	return (
	
			<Routes>
				<Route path="/login" element= {<Login/>}/>
				<Route path="/sign-up" element= {<SignUp />}/>
				<Route path ="/home" element= {<Home/>}/>
				<Route path='/Detalle' element= {<DetallesPeliculas/>}/>
				<Route path="*" element= {<PaginaNoEncontrada/>}/>
			</Routes>
	
	);
}

export default App

