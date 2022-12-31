import {signupStore} from "../../store/zustandSignup";
import {loginStore} from "../../store/zustandLogin";
import { useNavigate } from 'react-router-dom';
import {useCookies} from "react-cookie";
const UserButton = () => {
  const navigate = useNavigate();
  const {setLogin,loginPost,loginError,jwtStore}=loginStore()
  const { name, email, password,signupError,postUser } = signupStore();
  const [cookies, setCookie, removeCookie] = useCookies(['access_jwt']);
  const date = new Date();
  const signupButton = () => {
    const userObj = {
      name,
      email,
      password,
    };
    postUser("/user", userObj);
    if(signupError===false){
      date.setTime(date.getTime() + 30*60*1000);
      setCookie('access_jwt', jwtStore, {path:'/', expires:date});
      setLogin(true)
      navigate('/');
    }else{
      alert('조건에 맞지 많습니다')
    }

  };
  return <button onClick={signupButton}>Sign up</button>;
};
export default UserButton;
