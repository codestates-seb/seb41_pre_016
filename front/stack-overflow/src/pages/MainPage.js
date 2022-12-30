import LeftSideBar from "../components/LeftSideBar/LeftSideBar";
import { useEffect } from "react";
import { useCookies } from "react-cookie";
import { userInfoStore } from "../store/zustandUserInfo";

const MainPage = () => {
  const cookies = useCookies(["access_jwt"]);
  const { userInfo, isLoading, error, getToken } = userInfoStore();
  useEffect(() => {
    if (cookies.access_jwt !== undefined) {
      const cookieObj = {
        Authorization: cookies.access_jwt.Authorization,
        Refresh: cookies.access_jwt.Refresh,
      };
      getToken("/user", cookieObj);
    }
  }, []);

  return (
    <>
      <LeftSideBar />
      {error && <div>error</div>}
      {isLoading && <div>Loading</div>}
      {userInfo && console.log(userInfo)}
      <a href="/questions/ask">
        <button>Ask question</button>
      </a>
    </>
  );
};

export default MainPage;
