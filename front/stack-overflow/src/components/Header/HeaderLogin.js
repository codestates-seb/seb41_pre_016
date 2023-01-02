import styled from "styled-components";
import { ReactComponent as AchieveIcon } from "../../assets/achieveIcon.svg";
import { ReactComponent as HelpIcon } from "../../assets/helpIcon.svg";
import { ReactComponent as InBoxIcon } from "../../assets/inBoxIcon.svg";
import { ReactComponent as SwitcherIcon } from "../../assets/switcherIcon.svg";
import { ReactComponent as WinterbashIcon } from "../../assets/winterbashIcon.svg";
import { Link } from "react-router-dom";

const HeaderLogin = (props) => {
  const UserNav = styled.nav`
    padding-right: 12px;
    margin-left: auto;
    display: block;
    a {
      text-decoration: none;
    }
  `;
  const UserOl = styled.ol`
    display: flex;
    height: 100%;
    list-style: none;
    margin: 0;
    padding: 0;
    overflow-x: auto;
    margin-left: auto;
  `;
  const UserLi = styled.li`
    height: 48px;
    display: flex;
    text-decoration: none;
    white-space: nowrap;
    position: relative;
    background-color: var(--top-background-color);
    :hover {
      background-color: rgb(227, 230, 232);
    }
    .switcher {
      background-color: ${props.dropdown === true ? "rgb(227, 230, 232)" : ""};
    }
  `;
  const UserA = styled.div`
    display: inline-flex;
    align-items: center;
    padding: 0 12px;
    text-decoration: none;
    white-space: nowrap;
    position: relative;
  `;
  const IconA = styled.div`
    display: inline-flex;
    align-items: center;
    padding: 0 10px;
    text-decoration: none;
    white-space: nowrap;
    position: relative;
  `;
  const UserDiv = styled.div`
    width: 24px;
    height: 24px;
    display: flex;
    align-items: center;
    justify-content: center;
  `;
  const UserImg = styled.img`
    display: block;
    height: 24px;
    width: 24px;
    border-radius: 3px;
  `;
  const UserIconDiv = styled.div`
    border-radius: 3px;
    padding: 0 -2px;
    vertical-align: text-top;
    display: flex;
  `;
  const UserCardAwardDiv = styled.div`
    align-items: center;
    flex-direction: row;
    display: flex;
    gap: 4px;
  `;
  return (
    <UserNav>
      <UserOl>
        <Link to="/">
          <UserLi role="none">
            <UserA>
              <UserDiv>
                {/*<UserImg src={process.env.PUBLIC_URL + "/userIcon.png"}/>*/}
              </UserDiv>
              <UserCardAwardDiv>1</UserCardAwardDiv>
            </UserA>
          </UserLi>
        </Link>
        <Link to="/">
          <UserLi role="none">
            <IconA>
              <UserDiv>
                <UserIconDiv>
                  <InBoxIcon />
                </UserIconDiv>
              </UserDiv>
            </IconA>
          </UserLi>
        </Link>
        <Link to="/">
          <UserLi role="none">
            <IconA>
              <UserDiv>
                <UserIconDiv>
                  <AchieveIcon />
                </UserIconDiv>
              </UserDiv>
            </IconA>
          </UserLi>
        </Link>
        <Link to="/">
          <UserLi role="none">
            <IconA>
              <UserDiv>
                <UserIconDiv>
                  <HelpIcon />
                </UserIconDiv>
              </UserDiv>
            </IconA>
          </UserLi>
        </Link>
        <Link to="/">
          <UserLi role="none">
            <IconA>
              <UserDiv>
                <UserIconDiv>
                  <WinterbashIcon />
                </UserIconDiv>
              </UserDiv>
            </IconA>
          </UserLi>
        </Link>
        <UserLi role="none">
          <IconA onClick={props.removeHandler} className="switcher">
            <UserDiv>
              <UserIconDiv>
                <SwitcherIcon />
              </UserIconDiv>
            </UserDiv>
          </IconA>
        </UserLi>
      </UserOl>
    </UserNav>
  );
};
export default HeaderLogin;
