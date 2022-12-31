import LeftSideBar from "../components/LeftSideBar/LeftSideBar";
import { useEffect } from "react";
import { useCookies } from "react-cookie";
import { userInfoStore } from "../store/zustandUserInfo";
import { loginStore } from "../store/zustandLogin";
import { useNavigate } from "react-router-dom";

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
            {error && <div>error</div>}
            {isLoading && <div>Loading</div>}
            {userInfo && <div>{userInfo.name}님 안녕하세요</div>}
            <a href="/questions/ask">
                <button>Ask question</button>
            </a>
        </>
    );
};

export default MainPage;