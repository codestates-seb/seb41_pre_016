import styled from "styled-components";
import { useState } from "react";

const TagComponent = () => {
  const [tagItem, setTagItem] = useState("");
  const [tagList, setTagList] = useState([]);

  const onKeyPress = (e) => {
    if (e.target.value.length !== 0 && e.key === "Enter") {
      submitTagItem();
    }
  };
  const submitTagItem = () => {
    let updatedTagList = [...tagList];
    updatedTagList.push(tagItem);
    setTagList(updatedTagList);
    setTagItem("");
  };

  const deleteTagItem = (e) => {
    const deleteTagItem = e.target.parentElement.firstChild.innerText;
    const filteredTagList = tagList.filter(
      (tagItem) => tagItem !== deleteTagItem
    );
    setTagList(filteredTagList);
  };

  return (
    <ContentBodyTextFlexDiv style={{ position: "relative" }}>
      <TagBox>
        {tagList.map((tagItem, index) => {
          return (
            <TagItem key={index}>
              <Text>{tagItem}</Text>
              <Button onClick={deleteTagItem}>×</Button>
            </TagItem>
          );
        })}
        <TagInput
          type="text"
          placeholder="Press enter to add tags"
          tabIndex={2}
          onChange={(e) => setTagItem(e.target.value)}
          value={tagItem}
          onKeyPress={onKeyPress}
        />
      </TagBox>
    </ContentBodyTextFlexDiv>
  );
};
const ContentBodyTextFlexDiv = styled.div`
  margin: 4px;
  flex-direction: column !important;
`;

const TagBox = styled.div`
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  min-height: 50px;
  border: 1px solid rgba(0, 0, 0, 0.3);
  border-radius: 3px;
  width: 100%;
  margin: 0;
  padding: 0.6em 0.7em;
`;

const TagItem = styled.span`
  margin: 2px;
  font-size: 13px;
  align-items: center;
  background-color: #e1ecf4;
  border-radius: 3px;
  color: hsl(205, 47%, 42%);
  padding: 4px;
  display: inline-flex;
  justify-content: center;
  min-width: 0;
  text-decoration: none;
  vertical-align: middle;
  white-space: nowrap;
`;

const Text = styled.span``;

const Button = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 16px;
  height: 16px;
  margin-left: 4px;
  background-color: inherit;
  color: hsl(205, 47%, 42%);
  align-self: center;
  border-radius: 3px;
  cursor: pointer;
  padding: 1px;
  font-size: 25px;
  :hover {
    background-color: hsl(205, 47%, 42%);
    color: #e1ecf4;
  }
`;

const TagInput = styled.input`
  width: max-content;
  margin: 0;
  padding: 0.6em 0.7em;
  border: none;
  background-color: var(--white);
  color: var(--fc-dark);
  font-size: var(--fs-body1);
  font-family: inherit;
  box-sizing: inherit;
  display: block;
`;
export default TagComponent;
