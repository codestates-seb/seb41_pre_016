import styled from "styled-components";
import HeaderNoLogin from "./HeaderNoLogin";
import HeaderLogin from "./HeaderLogin";
import { ReactComponent as Logo } from "../../assets/logo-stackoverflow.svg";
import { ReactComponent as SearchIcon } from "../../assets/searchIcon.svg";
import HeaderDropDown from "./HeaderDropDown";
import { loginStore } from "../../store/zustandLogin";
import { Link, useNavigate } from "react-router-dom";
import { userInfoStore } from "../../store/zustandUserInfo";
import { useCookies } from "react-cookie";
import { useEffect, useState, useRef } from "react";

const Header = () => {
  const [cookies, setCookie, removeCookie] = useCookies(["access_jwt"]);
  const { userInfo, isLoading, error, setIsLoading, setError, getToken } =
    userInfoStore();
  const { isLogin, setLogin } = loginStore();
  const navigate = useNavigate();

  const [dropdown, setDropdown] = useState(false);
  const dropdownRef = useRef();

  // dropdown 이 열려있고,
  // 이벤트타겟과 ref 대상이 일치하지 않으면 dropdown 을 false 로 변경함
  const handleCloseDropdown = (event) => {
    if (dropdown && !dropdownRef.current.contains(event.target)) {
      setDropdown(false);
    }
  };

  const handleToggleDropdown = () => {
    setDropdown(!dropdown);
  };

  useEffect(() => {
    if (cookies.access_jwt !== undefined) {
      const cookieObj = {
        Authorization: cookies.access_jwt.Authorization,
        Refresh: cookies.access_jwt.Refresh,
      };
      getToken("/user", cookieObj);
      setLogin(true);
      console.log(userInfo);
    } else {
      navigate("/login");
    }

    // 컴포넌트가 로드될때, 이벤트 추가
    window.addEventListener("mousedown", handleCloseDropdown);

    // 컴포넌트가 unmount 될때 이벤트 삭제
    return () => {
      window.removeEventListener("mousedown", handleCloseDropdown);
    };
  }, []);

  const Header = styled.div`
    z-index: 1;
    margin-top: -50px;
    position: fixed;
    border-top: 3px solid var(--orange);
    background-color: var(--top-background-color);
    display: flex;
    min-width: auto;
    box-shadow: var(--bs-sm);
    width: 100%;
    align-items: center;
    height: 50px;
    --orange: #f48225;
    --top-background-color: #f8f9f9;
    --s-full: 97.2307692rem;
    --bs-sm: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
      0 2px 8px hsla(0, 0%, 0%, 0.05);
    --bt-hover-color: hsl(210, 8%, 90%);
    --main-bar-search-border: hsl(210, 8%, 75%);
    --main-bar-search-textcolor: hsl(210, 8%, 25%);

    li {
      list-style: none;
    }
  `;
  const ContainerDiv = styled.div`
    margin: 0px auto;
    width: var(--s-full);
    max-width: 1264px;
    height: 100%;
    display: flex;
    align-items: center;
  `;
  const LogoA = styled.div`
    padding: 0 8px;
    height: 100%;
    display: flex;
    align-items: center;
    :hover {
      background-color: #e3e6e8;
    }
  `;
  const NavOl = styled.ol`
    padding: 2px 0;
    display: flex;
    font-family: unset;
  `;
  const TopBarButton = styled.button`
    background-color: var(--top-background-color);
    padding: 6px 10px;
    align-items: center;
    border: none;
    border-radius: 1000px;
    box-shadow: none;
    display: flex;
    position: relative;
    color: hsl(210, 8%, 45%);
    font-size: 13px;
    font-family: unset;
    font-weight: 600;

    :hover {
      background-color: var(--bt-hover-color);
    }
  `;
  const SearchForm = styled.form`
    padding: 0 8px;
    display: flex;
    align-items: center;
    flex-grow: 1;
  `;
  const SearchDiv = styled.div`
    position: relative;
    display: block;
    flex-grow: 1;
  `;
  const SearchInput = styled.input`
    border: 1px solid var(--main-bar-search-border);
    display: block;
    padding: 0.6em 0.7em;
    padding-left: 32px;
    font-size: 13px;
    font-family: inherit;
    background-color: white;
    border-radius: 3px;
    -webkit-appearance: none;
    max-width: 674.7px;
    width: 100%;
    :focus {
      outline: 1px solid var(--blue-400);
      box-shadow: 0 0 5px var(--blue-400);
    }
  `;
  const SearchIconDiv = styled.div`
    position: absolute;
    top: 50%;
    right: auto;
    left: 0.7em;
    margin-top: -9px;
    pointer-events: none;
  `;

  const HeaderContainer = styled.div`
    z-index: 9999;
  `;
  return (
    <Header>
      {dropdown ? (
        <HeaderContainer ref={dropdownRef}>
          <HeaderDropDown handleToggleDropdown={handleToggleDropdown} />
        </HeaderContainer>
      ) : (
        <></>
      )}
      <ContainerDiv>
        <Link to="/">
          <LogoA>
            <Logo />
          </LogoA>
        </Link>
        <NavOl>
          <li>{isLogin ? <></> : <TopBarButton>About</TopBarButton>}</li>
          <li>
            <TopBarButton onClick={() => console.log(isLogin)}>
              Product
            </TopBarButton>
          </li>
          <li>{isLogin ? <></> : <TopBarButton>For Teams</TopBarButton>}</li>
        </NavOl>
        <SearchForm>
          <SearchDiv>
            <SearchInput
              type="text"
              role="combobox"
              placeholder="Search..."
              maxLength="240"
            />
            <SearchIconDiv>
              <SearchIcon />
            </SearchIconDiv>
          </SearchDiv>
        </SearchForm>
        {isLogin ? (
          <HeaderLogin
            dropdown={dropdown}
            removeHandler={handleToggleDropdown}
          />
        ) : (
          <HeaderNoLogin />
        )}
      </ContainerDiv>
    </Header>
  );
};
export default Header;
