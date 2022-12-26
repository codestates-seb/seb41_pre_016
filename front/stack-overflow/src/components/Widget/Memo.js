import styled from "styled-components";
import { ReactComponent as Pencil } from "../../statics/Icons/pencil.svg";
import { Logo, Tooltip } from "../../statics/Icons/Sprites";

const Container = styled.div`
  background-color: var(--yellow-050);
  border-color: var(--yellow-200);
  border-radius: 3px;
  box-shadow: var(--bs-sm);
  position: relative;
  margin-bottom: 16px;
  font-size: 13px;
  border-top-right-radius: 3px;
  border-top-left-radius: 3px;
  border: 1px solid var(--yellow-200);
`;

const Header = styled.ul`
  display: block;
  color: #525960;
  background-color: var(--yellow-100);
  font-size: 12px;
  line-height: 16px;
  font-weight: 700;
  padding: 12px 15px;
  border-bottom: 1px solid var(--yellow-200);
  border-top: 1px solid var(--yellow-200);
`;

const List = styled.li`
  display: flex;
  padding-left: 16px;
  padding-right: 16px;
  margin: 12px 0px;
`;

const Icon = styled.div`
  flex-basis: 8.33333333%;
  flex-shrink: 0;
  vertical-align: text-top;
`;

const Text = styled.div`
  display: inline-block;
  vertical-align: top;
  color: var(--black-700);
  min-width: 0%;
  overflow-wrap: break-word;
  line-height: 17px;
`;

const Memo = () => {
  return (
    <Container>
      <Header>The Overflow Blog</Header>
      <List>
        <Icon>
          <Pencil />
        </Icon>
        <Text>I spent two years trying to do what Backstage does for free</Text>
      </List>
      <List>
        <Icon>
          <Pencil />
        </Icon>
        <Text>
          The complete guide to protecting your APIs with OAuth2 (part 1)
        </Text>
      </List>
      <Header>Featured on Meta</Header>
      <List>
        <Icon>
          <Tooltip />
        </Icon>
        <Text>Navigation and UI research starting soon</Text>
      </List>
      <List>
        <Icon>
          <Logo />
        </Icon>
        <Text>
          2022 Community Moderator Election Results - now with two more mods!
        </Text>
      </List>
      <List>
        <Icon>
          <Logo />
        </Icon>
        <Text>Temporary policy: ChatGPT is banned</Text>
      </List>
      <List>
        <Icon>
          <Logo />
        </Icon>
        <Text>I'm standing down as a moderator</Text>
      </List>
      <Header>Hot Meta Posts</Header>
      <List>
        <Icon>
          <Pencil />
        </Icon>
        <Text>How much self-promotion is too much?</Text>
      </List>
    </Container>
  );
};

export default Memo;
