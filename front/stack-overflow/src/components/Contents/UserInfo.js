import styled from "styled-components";
import useElapsedTime from "../../hooks/useElapsedTime";

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

const Reputation = styled.span`
  color: var(--black-600);
  font-weight: bold;
`;

const Time = styled.a`
  color: var(--black-500);
  text-decoration: none;
`;

const UserInfo = ({ name, userId, modTime }) => {
  const { elapsedTimeString } = useElapsedTime(modTime);

  return (
    <Container>
      <ProfileImage src="http://www.gravatar.com/avatar/00?d=mm" />
      <Nickname>{name}</Nickname>
      {/* <Reputation>4,493</Reputation> */}
      <Time href={`http://localhost:3000/user/${userId}`}>
        asked {elapsedTimeString} ago
      </Time>
    </Container>
  );
};

export default UserInfo;
