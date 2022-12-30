import styled from "styled-components";
import { ReactComponent as UpvoteBtn } from "../../assets/upvoteBtn.svg";
import { ReactComponent as DownvoteBtn } from "../../assets/downvoteBtn.svg";

const Container = styled.div`
  width: auto;
  padding-right: 16px;
  grid-column: 1;
`;

const ButtonList = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: -2px;
  color: var(--black-200);

  & > svg {
    margin: 0 auto;
    cursor: pointer;
  }
`;

const VoteCount = styled.div`
  margin: 0 auto;
  color: var(--black-500);
  font-size: 21px;
  line-height: 27px;
`;

const Aside = ({ votes }) => {
  return (
    <Container>
      <ButtonList>
        <UpvoteBtn fill="var(--black-200)" />
        <VoteCount>{votes}</VoteCount>
        <DownvoteBtn fill="var(--black-200)" />
      </ButtonList>
    </Container>
  );
};

export default Aside;
