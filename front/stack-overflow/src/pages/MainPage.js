import LeftSideBar from "../components/LeftSideBar/LeftSideBar";
import { useEffect } from "react";
import { useCookies } from "react-cookie";
import { userInfoStore } from "../store/zustandUserInfo";
import { loginStore } from "../store/zustandLogin";
import { useNavigate } from "react-router-dom";
import Question from "./Questions/Questions";

const MainPage = () => {
  const { userInfo, isLoading, error, setIsLoading, setError, getToken } =
    userInfoStore();

  return (
    <>
      <LeftSideBar />
      {userInfo && <Question />}
      {isLoading && <div>Loading</div>}
    </>
  );
};

export default MainPage;
