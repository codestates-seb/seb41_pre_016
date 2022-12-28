import { userStore } from "../../store/zustandUser";
import {loginStore} from "../../store/zustandLogin";
import { useNavigate } from 'react-router-dom';
const UserButton = () => {
  const navigate = useNavigate();
  const {isLogin,setLogin}=loginStore()
  const { name, email, password, postUser } = userStore();
  const signupButton = () => {
    const userObj = {
      name,
      email,
      password,
    };
    postUser("/user", userObj);
    setLogin(true)
    navigate('/');
  };
  return <button onClick={signupButton}>Sign up</button>;
};
export default UserButton;
