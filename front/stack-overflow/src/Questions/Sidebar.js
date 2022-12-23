import Memo from "./Widgets/Memo";
import Tagbox from "./Buttons/Tagbox";
import Silver from "./Widgets/Silver";
import styled from "styled-components";

const Container = styled.div`
  float: left;
  width: 300px;
  margin-left: 24px;
`;

const Sidebar = () => {
  return (
    <Container>
      <Memo />
      <Silver header="Custom Filter" contents="Create a custom filter" />
      <Silver
        header="Watched Tags"
        contents={<Tagbox taglist={["javascript", "reactjs"]} />}
        bold="700"
      />
    </Container>
  );
};

export default Sidebar;
