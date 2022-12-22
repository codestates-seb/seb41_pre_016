import styled from "styled-components";

const Container = styled.div`
  display: flex;
  height: 40px;
  align-items: center;
  margin-left: auto;
  gap: 4px;
`;

const ProfileImage = styled.img`
  width: 16px;
  height: 16px;
  border-radius: 3px;
`;

const Nickname = styled.a`
  color: var(--blue-600);
  cursor: pointer;
`;

const Asked = styled.span`
  color: var(--black-600);
  font-weight: bold;
`;

const Time = styled.a`
  color: var(--black-500);
`;

const UserInfo = () => {
  return (
    <Container>
      <ProfileImage src="http://www.gravatar.com/avatar/00?d=mm" />
      <Nickname>PHILLIPP APETENOK</Nickname>
      <Asked>4,493</Asked>
      <Time>asked 24 mins ago</Time>
    </Container>
  );
};

export default UserInfo;
