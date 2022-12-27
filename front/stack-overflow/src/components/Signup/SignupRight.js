import styled from "styled-components";
import { ReactComponent as GoogleSvg } from "../../assets/googleIcon.svg";
import { ReactComponent as AlertCircleIcon } from "../../assets/alertCircleIcon.svg";
import { ReactComponent as TextBottomIcon } from "../../assets/textBottomIcon.svg";
import { useState } from "react";

const SignupRight = () => {
  let name = "";
  let email = "";
  let password = "";
  const [emailErr, setEmailErr] = useState(false);
  const [passwordErr, setPasswordErr] = useState(false);
  const [userErr, setUserErr] = useState(false);
  let rule = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
  const checkUser = () => {
    if (!email) {
      setEmailErr(true);
    } else {
      setEmailErr(false);
    }
    if (!password || !rule.test(password)) {
      setPasswordErr(true);
    } else {
      setPasswordErr(false);
    }
  };
  const ContainDiv = styled.div`
    flex-shrink: 0 !important;
  `;
  const OAuthDiv = styled.div`
    max-width: calc(var(--s-step) * 3) !important;
    display: flex !important;
    margin: -4px 0 16px 0;
    flex-direction: column !important;
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
      align-items: center;
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
  const SignupLabel = styled.label`
    font-size: 15px;
    color: var(--fc-dark);
    font-family: inherit;
    font-weight: 600;
    padding: 0 2px;
  `;
  const SignupRelativeDiv = styled.div`
    margin: 4px 0;
    position: relative;
    display: flex;
  `;
  const DisplayNameInput = styled.input`
    width: 100%;
    margin: 0;
    padding: 0.6em 0.7em;
    border: 1px solid var(--bc-darker);
    border-radius: 3px;
    background-color: var(--white);
    color: var(--fc-dark);
    font-size: var(--fs-body1);
    font-family: inherit;
    :focus {
      outline: 1px solid var(--blue-400);
      box-shadow: 0 0 5px var(--blue-400);
    }
  `;
  const EmailInput = styled.input`
    width: 100%;
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
  const PasswordInput = styled.input`
    width: 100%;
    margin: 0;
    padding: 0.6em 0.7em;
    border: 1px solid var(--bc-darker);
    border-radius: 3px;
    ${passwordErr
      ? "border: 1px solid var(--red-400);"
      : "border: 1px solid var(--bc-darker);"}
    background-color: var(--white);
    color: var(--fc-dark);
    font-size: var(--fs-body1);
    font-family: inherit;
    :focus {
      ${passwordErr
        ? "outline: 1px solid var(--red-400); box-shadow: 0 0 5px var(--red-400);"
        : "outline: 1px solid var(--blue-400); box-shadow: 0 0 5px var(--blue-400)"}
    }
  `;
  const PasswordRelativeDiv = styled.div`
    margin: 2px 0;
    position: relative;
    display: flex !important;
    align-items: center;
  `;
  const SignupButtonDiv = styled.div`
    margin: 6px 0;
    display: flex !important;
    flex-direction: column !important;
  `;
  const SignupButton = styled.button`
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
    :hover {
      background-color: rgb(0, 116, 204);
    }
  `;
  const MarginDiv = styled.div`
    margin-bottom: 12px;
  `;
  const ScriptRuleDiv = styled.div`
    color: var(--fc-light) !important;
    font-size: 12px !important;
    margin-bottom: 4px;
    margin-top: 4px;
  `;
  const RecapchaDiv = styled.div`
    margin: 6px 0;
    padding: 8px 0 2px 0;
    display: flex !important;
    justify-content: center !important;
    align-items: center !important;
    background-color: var(--black-050) !important;
    border-radius: var(--br-sm) !important;
    border: 1px solid var(--black-075);
    width: 268px;
    height: 156px;
  `;
  const CheckAreaDiv = styled.div`
    margin: 6px 0;
    div:first-child {
      display: flex !important;
      div:first-child {
        padding-top: 4px;
        margin-right: 4px;
      }
      div:nth-child(2) {
        margin-right: 2px;
      }
    }
  `;
  const CheckAreaInput = styled.input`
    border-radius: 3px;
    background-position: center center;
    background-repeat: no-repeat;
    background-size: contain;
    appearance: none;
    margin: 0;
    width: 1em;
    height: 1em;
    border: 1px solid var(--bc-darker);
    background-color: var(--white);
    outline: 0;
    font-size: inherit;
  `;
  const CheckAreaLabel = styled.label`
    cursor: pointer;
    font-weight: 400 !important;
    font-size: 12px !important;
    color: var(--fc-dark);
    font-family: inherit;
    padding: 0 var(--su2);
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
  const AlertCircleEmailDiv = styled.div`
    position: absolute;
    top: 50%;
    right: 0.7em;
    margin-top: -10px;
    pointer-events: none;
    ${emailErr | userErr ? "visibility: visible;" : "visibility: hidden;"}
  }
  `;
  const AlertCirclePasswordDiv = styled.div`
    position: absolute;
    top: 50%;
    right: 0.7em;
    margin-top: -18px;
    pointer-events: none;
    ${passwordErr | userErr ? "visibility: visible;" : "visibility: hidden;"}
  }
  `;
  const AlertP = styled.p`
    color: var(--red-500);
    margin: 2px 0;
    padding: var(--su2);
    font-size: var(--fs-caption);
  `;
  return (
    <ContainDiv>
      <OAuthDiv>
        <div>
          <GoogleButton>
            <GoogleSvg />
            <pre> Sign up with Google </pre>
          </GoogleButton>
        </div>
      </OAuthDiv>
      <FormContainerDiv>
        <div id="login-form">
          {/*Display name*/}
          <MarginDiv>
            <SignupLabel>Display Name</SignupLabel>
            <SignupRelativeDiv>
              <DisplayNameInput
                className="s-input"
                id="display-name"
                size="30"
                type="text"
                name="display-name"
              />
            </SignupRelativeDiv>
          </MarginDiv>
          {/*Email*/}
          <MarginDiv>
            <SignupLabel>Email</SignupLabel>
            <SignupRelativeDiv>
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
            </SignupRelativeDiv>
            <AlertP>
              {emailErr ? "Email cannot be empty." : " "}
              {userErr ? "The email is not a valid email address." : " "}
            </AlertP>
          </MarginDiv>
          {/*Pass*/}
          <div className="d-flex-password">
            <MarginDiv>
              <PasswordRelativeDiv>
                <SignupLabel>Password</SignupLabel>
              </PasswordRelativeDiv>
              <SignupRelativeDiv>
                <PasswordInput
                  className="s-input"
                  id="password"
                  type="password"
                  name="password"
                  autoComplete="off"
                  size="30"
                  onChange={(e) => {
                    password = e.target.value;
                  }}
                />
                <AlertCirclePasswordDiv>
                  <AlertCircleIcon />
                </AlertCirclePasswordDiv>
                <AlertP>
                  {passwordErr ? "Password cannot be empty." : " "}
                </AlertP>
              </SignupRelativeDiv>
            </MarginDiv>
          </div>
          <ScriptRuleDiv>
            Passwords must contain at least eight characters,
            <br /> including at least 1 letter and 1 number.
          </ScriptRuleDiv>
          {/*recapcha*/}
          <RecapchaDiv />
          {/*checkArea*/}
          <CheckAreaDiv>
            <div>
              <div>
                <CheckAreaInput
                  type="checkbox"
                  name="EmailOptIn"
                  id="opt-in"
                  className="s-checkbox"
                  data-ga-action="Email Opt-In"
                  data-ga-is-pii="false"
                />
              </div>
              <div>
                <CheckAreaLabel
                  htmlFor="opt-in"
                  className="s-label fw-normal fs-caption px0"
                >
                  Opt-in to receive occasional product <br />
                  updates, user research invitations, company
                  <br /> announcements, and digests.
                </CheckAreaLabel>
              </div>
              <div></div>
            </div>
          </CheckAreaDiv>
          {/* login Button */}
          <SignupButtonDiv>
            <SignupButton onClick={checkUser}>Sign up</SignupButton>
          </SignupButtonDiv>
        </div>
      </FormContainerDiv>
      <ScriptDiv>
        <ContentsDiv>
          <p>Already have an account? </p>
          <a href="/login">Log in</a>
        </ContentsDiv>
        <ContentsDiv>
          <p>Are you an employer?</p>
          <a href="/front/stack-overflow/public">Sign up on Talent</a>
          <TextBottomIcon />
        </ContentsDiv>
      </ScriptDiv>
    </ContainDiv>
  );
};
export default SignupRight;
