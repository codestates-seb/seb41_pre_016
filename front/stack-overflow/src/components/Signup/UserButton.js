import { signupStore } from "../../store/zustandSignup";
import { loginStore } from "../../store/zustandLogin";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";
const UserButton = () => {
  const navigate = useNavigate();
  const { setLogin, loginPost } = loginStore();
  const { name, email, password, signupError, postUser } = signupStore();
  const [cookies, setCookie, removeCookie] = useCookies(["access_jwt"]);
  const date = new Date();
  const signupButton = async () => {
    const userObj = {
      name,
      email,
      password,
    };
    await postUser("/user", userObj);
    await navigate("/login");
  };
  return <button onClick={signupButton}>Sign up</button>;
};
export default UserButton;
