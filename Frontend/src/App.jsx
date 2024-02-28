import {Routes, Route} from "react-router-dom"
import Login from "./pages/Login/Login"
import SignUp from "./pages/SignUp/SignUp";
import Home from "./pages/Home/Home";
import DetallesPeliculas from "./pages/Detalle/DetallesPeliculas"
import PaginaNoEncontrada from "./pages/PaginaNoEncontrada/PaginaNoEncontrada"
import TopRanking from "./pages/TopRanking/TopRanking";
import LayoutDashboard from "./components/LayoutDashboard/LayoutDashboard";


function App() {
	
	return (
	
			<Routes>
				<Route path="/login" element= {<Login/>}/>
				<Route path="/sign-up" element= {<SignUp />}/>
				<Route path ="/home" element= {<Home/>}/>
			</Routes>
	
	);
}

export default App

