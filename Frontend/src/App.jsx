import {Routes, Route} from "react-router-dom"
import Login from "./pages/Login/Login"
import SignUp from "./pages/SignUp/SignUp";
import Home from "./pages/Home/Home";

function App() {
	
	return (
	
			<Routes>
				<Route path="/login" element= {<Login/>}/>
				<Route path="/sign-up" element= {<SignUp />}/>
				<Route path ="/" element= {<Home/>}/>
			</Routes>
	
	);
}

export default App
