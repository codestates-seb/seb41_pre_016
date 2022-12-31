import styled from "styled-components";
import useStore from "../../store/ArticleStates";
import useElapsedTime from "../../hooks/useElapsedTime";
import { useEffect } from "react";

const Container = styled.div``;

const Title = styled.h1`
  // 줄바꿈 처리
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-between;
  color: var(--black-700);
  line-height: 36px;
  font-size: 27px;
  overflow-wrap: break-word;
  margin-bottom: 8px;
`;

const InfoContainer = styled.div`
  font-size: 13px;
  line-height: 25px;
  display: flex;
  color: var(--black-075);
  flex-wrap: wrap;
  padding-bottom: 8px;
  margin-bottom: 16px;
  border-bottom: 1px solid var(--black-075);
`;

const InfoContents = styled.span`
  color: var(--fc-light);
  margin-right: 16px;
  margin-bottom: 8px;

  span {
    &:first-child {
      color: var(--black-900);
    }
  }
`;

const Header = () => {
  const { title, createdAt, modifiedAt, views } = useStore();
  const createdTime = useElapsedTime(createdAt);
  const modifiedTime = useElapsedTime(modifiedAt);

  return (
    <Container>
      <Title> {title} </Title>
      <InfoContainer>
        <InfoContents>
          Asked <span>{createdTime.elapsedTimeString} ago</span>
        </InfoContents>
        <InfoContents>
          Modified <span>{modifiedTime.elapsedTimeString} ago</span>
        </InfoContents>
        <InfoContents>
          Viewed <span>{views} times</span>
        </InfoContents>
      </InfoContainer>
    </Container>
  );
};

export default Header;
