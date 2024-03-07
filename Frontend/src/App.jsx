import { Routes, Route } from "react-router-dom"
import Login from "./pages/Login/Login"
import SignUp from "./pages/SignUp/SignUp";
import Home from "./pages/Home/Home";
import DetallesPeliculas from "./pages/Detalle/DetallesPeliculas"
import PaginaNoEncontrada from "./pages/PaginaNoEncontrada/PaginaNoEncontrada"
import TopRanking from "./pages/TopRanking/TopRanking";
import LayoutDashboard from "./components/LayoutDashboard/LayoutDashboard";
import Favorite from "./pages/Favorite/Favorite";
import { AuthContextProvider } from "./context/AuthContext/AuthContext";
import PrivateRoute from "./components/PrivateRoute/PrivateRoute ";


function App() {
	
	return (
		<AuthContextProvider>
			<Routes>
				<Route path="/" element={<Login />} />
				<Route path="/sign-up" element={<SignUp />} />
				<Route path="*" element={<PaginaNoEncontrada />} />
				<Route element={<PrivateRoute />}>
					<Route path="/dashboard" element={<LayoutDashboard><Home /></LayoutDashboard>} />
					<Route path="/top-ranking" element={<LayoutDashboard><TopRanking /></LayoutDashboard>} />
					<Route path="/Detalle/:id" element={<LayoutDashboard><DetallesPeliculas /></LayoutDashboard>} />
					<Route path="/favorite" element={<LayoutDashboard><Favorite /></LayoutDashboard>} />
				</Route>
			</Routes>
		</AuthContextProvider>
	);
}

export default App