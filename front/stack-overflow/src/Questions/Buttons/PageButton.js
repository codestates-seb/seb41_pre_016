import styled from "styled-components";

const Container = styled.div`
  display: flex;
  align-items: center;
  font-size: 13px;
  line-height: 25px;
  padding: 0 8px;
  border-radius: 3px;
  color: var(--fc-medium);
  border: 1px solid var(--bc-medium);
  background-color: transparent;
`;

const Selected = styled(Container)`
  color: white;
  border: 1px solid transparent;
  background-color: rgb(244, 130, 37);
`;

const PageButton = ({ number, selected }) => {
  return (
    <>
      {selected ? (
        <Selected>{number}</Selected>
      ) : (
        <Container>{number}</Container>
      )}
    </>
  );
};

export default PageButton;
