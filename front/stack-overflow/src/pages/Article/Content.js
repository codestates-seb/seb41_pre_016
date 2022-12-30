import styled from "styled-components";
import Tagbox from "../../components/Buttons/Tagbox";
import useStore from "../../store/ArticleStates";

//http://www.gravatar.com/avatar/00?d=mm

const Signature = styled.div`
  display: flex;
  text-align: left;
  vertical-align: top;
  width: 200px;
  background-color: ${(props) => (props.author ? "#D9EAF7" : "white")};
  border-radius: 3px;
  padding: 7px;
`;
const Container = styled.div`
  grid-column: 2;
  padding-right: 16px;
  line-height: 23px;
  width: auto;
  min-width: 0;
`;

const Footer = styled.div`
  display: flex;
  font-size: 13px;
  color: var(--black-400);
  justify-content: flex-end;
  align-items: flex-start;
  margin-top: 24px;
  margin-bottom: 16px;
`;

const EditButton = styled.div`
  flex: 1 auto;
  margin-right: 16px;
  cursor: pointer;
`;

const ProfileWrapper = styled.img`
  width: 32px;
  height: 32px;
  border-radius: 3px;
`;

const ProfileDetail = styled.div`
  margin-left: 8px;
  width: calc(100% - 40px);
  float: left;
  line-height: 17px;
  word-wrap: break-word;
  cursor: pointer;
  color: var(--blue-600);
  &:hover {
    color: var(--blue-500);
  }
`;

const TagContainer = styled.div`
  margin-top: 20px;
  margin-bottom: 40px;
`;
const Content = ({ content, tags, name, author, id }) => {
  const authorProps = {};
  if (author) authorProps.author = true;

  return (
    <Container>
      <div>{content}</div>
      <TagContainer>
        <Tagbox taglist={tags} />
      </TagContainer>
      <Footer>
        <EditButton>Share　Edit　Flow</EditButton>
        <Signature {...authorProps}>
          <ProfileWrapper src="http://www.gravatar.com/avatar/00?d=mm" />
          <ProfileDetail>{name}</ProfileDetail>
        </Signature>
      </Footer>
    </Container>
  );
};

export default Content;
