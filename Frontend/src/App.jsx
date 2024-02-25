import {Routes, Route, BrowserRouter} from "react-router-dom"
import DetallePeliculas from "./pages/Detalle/DetallesPeliculas"
import PaginaNoEncontrada from "./pages/PaginaNoEncontrada/PaginaNoEncontrada"
import Login from "./pages/Login/Login"
import SignUp from "./pages/SignUp/SignUp";


function App() {

	return (
		<div>
			<BrowserRouter>
			<Routes>
			<Route path="/login" element= {<Login/>}/>
				<Route path="/sign-up" element= {<SignUp />}/>
				<Route path='/Detalle' element= {<DetallePeliculas/>}/>
				<Route path="*" element= {<PaginaNoEncontrada/>}/>
			</Routes>
		</BrowserRouter>
		

		</div>
		
	);
}








export default App
