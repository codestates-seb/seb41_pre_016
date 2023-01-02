import LeftSideBar from "../components/LeftSideBar/LeftSideBar";
import { useEffect } from "react";
import { useCookies } from "react-cookie";
import { userInfoStore } from "../store/zustandUserInfo";
import { loginStore } from "../store/zustandLogin";
import { useNavigate } from "react-router-dom";
import Question from "./Questions/Questions";

const MainPage = () => {
  const [cookies, setCookie, removeCookie] = useCookies(["access_jwt"]);
  const { userInfo, isLoading, error, setIsLoading, setError, getToken } =
    userInfoStore();
  const { isLogin, setLogin } = loginStore();

  const navigate = useNavigate();
  useEffect(() => {
    if (cookies.access_jwt !== undefined) {
      const cookieObj = {
        Authorization: cookies.access_jwt.Authorization,
        Refresh: cookies.access_jwt.Refresh,
      };
      getToken("/user", cookieObj);
      setLogin(true);
    } else {
      navigate("/login");
    }
  }, []);

  return (
    <>
      <LeftSideBar />
      {userInfo && <Question />}
      {isLoading && <div>Loading</div>}
    </>
  );
};

export default MainPage;
