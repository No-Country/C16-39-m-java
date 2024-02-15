import {Routes, Route, BrowserRouter} from "react-router-dom"
// import Login from "./pages/Login/Login"
import SignUp from "./pages/SignUp/SignUp";


function App() {
	
	return (
		<BrowserRouter>
			<Routes>
				{/* <Route path="/login" element= {<Login/>}/> */}
				<Route path="/sign-up" element= {<SignUp />}/>
			</Routes>
		</BrowserRouter>
	);
}

export default App
