import { useForm } from "react-hook-form";
import axios from "axios"
//import backgroundImage from "./background.png";
import fondoPortada from "./images/fondoPortada.png"
import logo4 from "./images/logo4.png"

import {useNavigate} from "react-router-dom"

const Login = () => {

  const navigate = useNavigate();
  // register nos sirve para registrar los campos y validaciones
  //handleSubmit: para el envio de formulario
  const {
    register,
    formState: { errors },
    handleSubmit,
    // watch,
  } = useForm();

  //recibimos los datos del formulario
  const onSubmit = async (data) => {
    console.log(data);
    console.log(errors);

    
    try {


      const serverProd = 'https://movies-apirest-c77e9f5e2ba2.herokuapp.com'
      const path = '/users/auth'
      //const response = await axios.post ('https://movies-apirest-c77e9f5e2ba2.herokuapp.com/users/auth',data);
      const fullURL = await axios.post (`${serverProd}${path}`,data)
      const result= fullURL.data;
      console.log(result);

    navigate("/dashboard");

      return result

    } catch (error) {
      console.error("Error login user:", error);
          alert("Error login user. Please try again.");
    }
  };

  // const watchEmail = watch("email");
  // const watchPassword = watch("password");

  return (
    <div className="flex items-center justify-center h-screen font-poppins">
      
      <div className="w-1/2 bg-opacity-95 p-6 rounded shadow flex flex-col items-center">

      <img src={logo4} alt="Logo" className="absolute top-0 left-0 w-34 h-32" />

        <h2 className="text-3xl mb-4 text-center text-white -translate-y-10">
          Bienvenido/a de nuevo
        </h2>
        <p className="mb-4 text-center text-white text-opacity-50  -translate-y-8">
          Por favor ingrese sus datos
        </p>
        <form
          onSubmit={handleSubmit(onSubmit)}
          autoComplete="off"
          className="space-y-4"
        >
          <div className="w-full max-w-md">
            <label htmlFor="Email" className="block">
              
            </label>
            <input
              type="text"
              name="email"
              autoComplete="off"
              placeholder="Ingrese su email"
              {...register("email", {
                required: "El campo es requerido",
                pattern: {
                  value: /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/,
                  message: "El formato del email es incorrecto",
                },
              })}
              className="block w-full p-2 border-b border-gray-300 bg-transparent mt-1"
            />
            {errors.email && <span className="text-red-500">{errors.email.message}</span>}
          </div>

          <div className="w-full max-w-md">
            <label htmlFor="Password" className="block">
              
            </label>
            <input
              type="password"
              name="password"
              autoComplete="off"
              placeholder="Ingrese su password"
              {...register("password", {
                required: "El campo es requerido",
                pattern: {
                  value: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/,
                  message:
                    "La contraseña debe tener mínimo ocho caracteres, al menos una letra y un número",
                },
              })}
              className="block w-full p-2 border-b border-gray-300 bg-transparent mt-1"
            />
            {errors.password && (
              <span className="text-red-500">{errors.password.message}</span>
            )}
          </div>
          <input
            type="submit"
            value="Iniciar sesion"
            className="w-full py-2 px-4 bg-white text-black font-bold rounded translate-y-12"
          />

          <p className="mt-4 text-center translate-y-12">
            ¿No tienes una cuenta?{" "}
            <a href="/sign-up" className="text-blue-500 hover:text-blue-700">
              Crear cuenta
            </a>
          </p>
        </form>
      </div>
      <div className="hidden md:block md:w-1/2">
        <div
          className="h-screen bg-center bg-cover"
          style={{
            backgroundImage: `url(${fondoPortada})`,
            backgroundSize: "cover",
            backdropFilter: "blur(20px)",
          }}
        ></div>
      </div>
    </div>
  );
};

export default Login;
