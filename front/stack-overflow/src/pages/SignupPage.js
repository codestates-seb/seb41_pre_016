import SignupRight from "../components/Signup/SignupRight";
import SignupLeft from "../components/Signup/SignupLeft";
import styled from "styled-components";

const ContainDiv = styled.div`
  max-width: 100%;
  justify-content: center;
  margin: 0;
  background-color: var(--black-050);
  width: 100%;
  background: none;
  display: flex;
  position: relative;
  flex: 1 0 auto;
  text-align: left;

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
`;
const ContentDiv = styled.div`
  width: 100%;
  max-width: 1264px;
  margin: 0;
  background-color: transparent;
  border-radius: 0;
  padding: 24px;
  box-sizing: border-box;
  height: 100%;
  display: flex !important;
  justify-content: center !important;
  align-items: center !important;
`;
const CenterFlexDiv = styled.div`
  display: flex !important;
  align-items: center !important;
`;
const SignupPage = () => {
  return (
    <ContainDiv>
      <ContentDiv>
        <CenterFlexDiv>
          <SignupLeft />
          <SignupRight />
        </CenterFlexDiv>
      </ContentDiv>
    </ContainDiv>
  );
};
export default SignupPage;
