import styled from "styled-components";
import PageButton from "../Buttons/PageButton";

const Container = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
  padding-top: 50px;
  margin: 20px 0;
`;

const Pagination = () => {
  return (
    <Container>
      <PageButton number="1" selected />
      <PageButton number="2" />
      <PageButton number="3" />
      <PageButton number="4" />
      <PageButton number="5" />
      <div style={{ padding: "5px" }}>...</div>
      <PageButton number="466965" />
      <PageButton number="Next" />
    </Container>
  );
};

export default Pagination;
