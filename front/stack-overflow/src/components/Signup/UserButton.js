import {signupStore} from "../../store/zustandSignup";
import {loginStore} from "../../store/zustandLogin";
import { useNavigate } from 'react-router-dom';
const UserButton = () => {
  const navigate = useNavigate();
  const {isLogin,setLogin,error}=loginStore()
  const { name, email, password,signupError,postUser } = signupStore();
  const signupButton = () => {
    const userObj = {
      name,
      email,
      password,
    };
    postUser("/user", userObj);
    if(signupError===false){
      setLogin(true)
      navigate('/');
    }else{
      alert('조건에 맞지 많습니다')
    }

  };
  return <button onClick={signupButton}>Sign up</button>;
};
export default UserButton;
