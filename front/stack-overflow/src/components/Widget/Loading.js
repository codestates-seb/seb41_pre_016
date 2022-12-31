import { PacmanLoader } from "react-spinners";
import styled from "styled-components";

const Container = styled.div`
  width: 100%;
  height: 50vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Loading = () => {
  return (
    <Container>
      <PacmanLoader color="var(--black-200)" />
    </Container>
  );
};

export default Loading;
