import { signupStore } from "../../store/zustandSignup";
import { loginStore } from "../../store/zustandLogin";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";
const UserButton = () => {
  const navigate = useNavigate();
  const { name, email, password, signupError, postUser } = signupStore();
  const signupButton = async () => {
    const userObj = {
      name,
      email,
      password,
    };
    await postUser(`/user/`, userObj);
  };
  return <button onClick={signupButton}>Sign up</button>;
};
export default UserButton;
