import styled from "styled-components";

const Container = styled.div`
  display: flex;
  flex-flow: row wrap;
  row-gap: 1px;
`;

const Tag = styled.button`
  display: inline;
  border-radius: 3px;
  border: 1px solid transparent;
  background-color: var(--powder-100);
  align-items: center;
  margin-right: 4px;
  &:hover {
    background-color: var(--powder-200);
  }
`;

const InlineText = styled.a`
  color: #2c5877;
  font-size: 12px;
`;

const Tagbox = ({ taglist }) => {
  return (
    <Container>
      {taglist
        ? taglist?.map((el) => {
            return (
              <Tag>
                <InlineText>{el}</InlineText>
              </Tag>
            );
          })
        : null}
    </Container>
  );
};

export default Tagbox;
