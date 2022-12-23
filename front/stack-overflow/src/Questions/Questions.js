import styled from "styled-components";
import BlueButton from "./Buttons/BlueButton";
import List from "./List";
import Sidebar from "./Sidebar";

const Container = styled.div`
  max-width: 1100px;
  width: calc(100% - 164);
  height: 100vh;
  background-color: white;
  padding: 24px;
  border-left: 1px solid hsl(210, 8%, 85%);
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI Adjusted",
    "Segoe UI", "Liberation Sans", sans-serif;
`;

const MainSection = styled.section`
  float: left;
  width: 728px;
  margin: 0;
  padding: 0;
`;

const Headbar = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin-bottom: 15px;
  & > h1 {
    font-size: 27px;
    font-weight: 400;
    flex: 1 auto !important;
    display: inline-block;
    vertical-align: middle;
  }
`;

const Questions = () => {
  return (
    <Container>
      <MainSection>
        <Headbar>
          <h1> All Questions </h1>
          <BlueButton text="Ask Question" />
        </Headbar>
        <List />
      </MainSection>
      <Sidebar />
    </Container>
  );
};

export default Questions;
