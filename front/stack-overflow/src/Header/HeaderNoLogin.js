import styled from "styled-components";


const HeaderNoLogin = () => {
    const LoginSignupNav = styled.nav`
      padding-right: 12px;
      margin-left: 40px;
      display: block;
    `
    const LoginSignupOl = styled.ol`
      display: flex;
      height: 100%;
      list-style: none;
      margin: 0;
      padding: 0;
    `
    const LoginSignupLi = styled.li`
      display: flex;
      align-items: center;
      justify-content: center;
    `
    const SignupButton = styled.button`
      border: 1px solid hsl(205, 41%, 63%);
      background-color: #E1ECF4;
      color: hsl(205, 47%, 42%);
      border-radius: 3px;
      box-shadow: inset 0 1px 0 0 hsla(0, 0%, 100%, 0.7);
      padding: 8px 10.4px;
      text-decoration: none;
      white-space: nowrap;
      position: relative;
      font-size:13px;

      :hover {
        background-color: hsl(205, 57%, 81%);
      }
    `
    const LoginButton = styled.button`
      border: 1px solid hsl(205, 41%, 63%);
      background-color: rgb(10, 149, 255);
      color: white;
      border-radius: 3px;
      box-shadow: inset 0 1px 0 0 hsla(0, 0%, 100%, 0.7);
      padding: 8px 10.4px;
      text-decoration: none;
      white-space: nowrap;
      position: relative;
      margin-left: 4px;
      font-size:13px;

      :hover {
        background-color: rgb(0, 116, 204);
      }
    `
    return (
        <LoginSignupNav>
            <LoginSignupOl>
                <LoginSignupLi>
                    <SignupButton>Log in</SignupButton>
                </LoginSignupLi>
                <LoginSignupLi>
                    <LoginButton>Sign up</LoginButton>
                </LoginSignupLi>
            </LoginSignupOl>
        </LoginSignupNav>
    )
}
export default HeaderNoLogin