import styled from "styled-components";
import { ReactComponent as StackoverflowSvg } from "./images/stackoverflowIcon.svg";
import { ReactComponent as GoogleSvg } from "./images/googleIcon.svg";
import { ReactComponent as AlertCircleIcon } from "./images/alertCircleIcon.svg";
import { ReactComponent as TextBottomIcon } from "./images/textBottomIcon.svg";

const Login = () => {
  const emailErr = true;
  const passwordErr = true;
  const userErr = false;
  const ContainerDiv = styled.div`
    height: 1000px;
    max-width: 100%;
    justify-content: center;
    width: 100%;
    display: flex;
    align-items: center;
    position: relative;
    text-align: left;
    background-color: var(--black-050);
    * {
      margin: 0;
      padding: 0;
      border: 0;
      font: inherit;
      font-size: 100%;
      vertical-align: baseline;
      box-sizing: inherit;
    }
    div {
      display: block;
    }
    svg {
      vertical-align: bottom;
    }
  `;
  const ContentDiv = styled.div`
    max-width: 1264px;
    background-color: transparent;
    padding: 24px;
    display: flex;
    justify-content: center;
  `;
  const FlexDiv = styled.div`
    display: flex;
    flex-direction: column;
    width: 278px;
  `;

  const StackoverflowIconDiv = styled.div`
    text-align: center;
    margin-bottom: 24px;
    font-size: var(--fs-title);
    margin-left: auto;
    margin-right: auto;
  `;
  const OauthLoginButtonDiv = styled.div`
    display: flex;
    margin: -4px auto 16px auto;
    flex-direction: column;
    flex: 1 auto;
  `;
  const MarginDiv = styled.div`
    margin-bottom: 12px;
  `;
  const GoogleButton = styled.div`
    margin: 4px 0;
    background-color: white;
    border: 1px solid var(--black-100);
    box-shadow: none;
    color: hsl(210, 8%, 25%);
    font-size: 13px;
    padding: 10.4px;
    cursor: pointer;
    font-family: inherit;
    font-weight: normal;
    line-height: var(--lh-sm);
    position: relative;
    outline: none;
    text-align: center;
    text-decoration: none;
    user-select: none;
    border-radius: 5px;
    box-sizing: inherit;
    &:first-child {
      display: flex;
      justify-content: center;
      flex-direction: row;
    }
    :hover {
      background-color: rgb(248, 249, 249);
    }
  `;
  const FormContainerDiv = styled.div`
    box-shadow: var(--bs-xl);
    margin: 0 auto 24px auto;
    background-color: var(--white);
    border-radius: 7px;
    padding: 24px;
  `;
  const LoginLabel = styled.label`
    font-size: var(--fs-body2);
    color: var(--fc-dark);
    font-family: inherit;
    font-weight: 600;
    padding: 0 2px;
  `;
  const LoginRelativeDiv = styled.div`
    margin: 4px 0;
    position: relative;
    display: flex;
  `;
  const AlertCircleEmailDiv = styled.div`
    position: absolute;
    top: 50%;
    right: 0.7em;
    margin-top: -12px;
    pointer-events: none;
    ${emailErr | userErr ? "visibility: visible;" : "visibility: hidden;"}
  }
  `;
  const AlertCirclePasswordDiv = styled.div`
    position: absolute;
    top: 50%;
    right: 0.7em;
    margin-top: -22px;
    pointer-events: none;
    ${passwordErr | userErr ? "visibility: visible;" : "visibility: hidden;"}
  }
  `;
  const EmailInput = styled.input`
    margin: 0;
    padding: 0.6em 0.7em;
    border: 1px solid var(--bc-darker);
    border-radius: 3px;
    ${emailErr | userErr
      ? "border: 1px solid var(--red-400);"
      : "border: 1px solid var(--bc-darker);"}
    background-color: var(--white);
    color: var(--fc-dark);
    font-size: var(--fs-body1);
    font-family: inherit;
    :focus {
      ${emailErr | userErr
        ? "outline: 1px solid var(--red-400); box-shadow: 0 0 5px var(--red-400);"
        : "outline: 1px solid var(--blue-400); box-shadow: 0 0 5px var(--blue-400)"}
    }
  `;
  const PasswordRelativeDiv = styled.div`
    margin: 2px 0;
    position: relative;
    display: flex !important;
    align-items: center;
    justify-content: space-between;
    a {
      text-decoration: none;
      color: var(--theme-link-color);
      cursor: pointer;
      user-select: auto;
      font-size: var(--fs-caption) !important;
    }
  `;
  const AlertP = styled.p`
    color: var(--red-500);
    margin: 2px 0;
    padding: var(--su2);
    font-size: var(--fs-caption);
  `;
  const LoginButtonDiv = styled.div`
    margin: 6px 0;
    display: flex !important;
    flex-direction: column !important;
  `;
  const LoginButton = styled.button`
    background-color: hsl(206, 100%, 52%);
    border: 1px solid transparent;
    border-radius: 3px;
    box-shadow: inset 0 1px 0 0 hsla(0, 0%, 100%, 0.4);
    color: white;
    font-size: 13px;
    padding: 0.8em;
    cursor: pointer;
    display: inline-block;
    font-family: inherit;
    font-weight: normal;
    line-height: var(--lh-sm);
    position: relative;
    outline: none;
    text-align: center;
    text-decoration: none;
    user-select: none;
  `;
  const ScriptDiv = styled.div`
    font-size: 13px !important;
    padding: 16px !important;
    margin-bottom: 24px !important;
  `;
  const ContentsDiv = styled.div`
    margin-top: 12px;
    display: flex !important;
    flex-direction: row !important;
    justify-content: center;
    a {
      margin: 0 3px;
      color: var(--theme-link-color);
      text-decoration: none;
      cursor: pointer;
    }
  `;
  return (
    //  stackoverflow 로그인
    <ContainerDiv>
      <ContentDiv>
        <FlexDiv>
          {/*stackoverflow 아이콘 부분*/}
          <StackoverflowIconDiv>
            <a href="/">
              <StackoverflowSvg></StackoverflowSvg>
            </a>
          </StackoverflowIconDiv>
          {/*stackoverflow oAuth로그인*/}
          <OauthLoginButtonDiv>
            <div>
              <GoogleButton>
                <GoogleSvg />
                <pre> Log in with Google </pre>
              </GoogleButton>
            </div>
          </OauthLoginButtonDiv>
          {/*로그인 박스*/}
          <FormContainerDiv>
            {/*Email*/}
            <form id="login-form">
              <MarginDiv>
                <LoginLabel>Email</LoginLabel>
                <LoginRelativeDiv>
                  <EmailInput
                    className="s-input"
                    id="email"
                    type="email"
                    size="30"
                    maxLength="100"
                    name="email"
                  />
                  <AlertCircleEmailDiv>
                    <AlertCircleIcon />
                  </AlertCircleEmailDiv>
                </LoginRelativeDiv>
                <AlertP>
                  {emailErr ? "Email cannot be empty." : " "}
                  {userErr ? "The email is not a valid email address." : " "}
                </AlertP>
              </MarginDiv>
              {/*Pass*/}
              <div className="d-flex-password">
                <MarginDiv>
                  <PasswordRelativeDiv>
                    <LoginLabel>Password</LoginLabel>
                    <a>Forgot password?</a>
                  </PasswordRelativeDiv>
                  <LoginRelativeDiv>
                    <EmailInput
                      className="s-input"
                      id="password"
                      type="password"
                      name="password"
                      autoComplete="off"
                      size="30"
                    />
                    <AlertCirclePasswordDiv>
                      <AlertCircleIcon />
                    </AlertCirclePasswordDiv>
                    <AlertP>
                      {passwordErr ? "Password cannot be empty." : " "}
                    </AlertP>
                  </LoginRelativeDiv>
                </MarginDiv>
              </div>
              {/* login Button */}
              <LoginButtonDiv>
                <LoginButton>Log in</LoginButton>
              </LoginButtonDiv>
            </form>
          </FormContainerDiv>
          <ScriptDiv>
            <ContentsDiv>
              <p>Don’t have an account? </p>
              <a href="/">Sign up</a>
            </ContentsDiv>
            <ContentsDiv>
              <p>Are you an employer? </p>
              <a href="/">Sign up on Talent</a>
              <TextBottomIcon />
            </ContentsDiv>
          </ScriptDiv>
        </FlexDiv>
      </ContentDiv>
    </ContainerDiv>
  );
};
export default Login;
