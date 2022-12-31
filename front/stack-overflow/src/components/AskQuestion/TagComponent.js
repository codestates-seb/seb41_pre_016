import styled from "styled-components";
import { useState } from "react";

const TagComponent = () => {
  const [tagItem, setTagItem] = useState("");
  const [tagList, setTagList] = useState([]);
  const { setTags } = QuestionStore();
  useEffect(() => {
    setTags(tagList);
  }, [tagList]);
  const onKeyPress = (e) => {
    if (e.target.value.length !== 0 && e.key === "Enter") {
      submitTagItem();
    }
  };
  const submitTagItem = () => {
    let updatedTagList = [...tagList];
    updatedTagList.includes(tagItem);
    if (updatedTagList.includes(tagItem) === false) {
      updatedTagList.push(tagItem);
      setTagList(updatedTagList);
    }
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
    <div className="ContentBodyTextFlexDiv" style={{ position: "relative" }}>
      <div className="TagBox">
        {tagList.map((tagItem, index) => {
          return (
            <span className="TagItem" key={index}>
              <p>{tagItem}</p>
              <button className="DeleteButton" onClick={deleteTagItem}>
                ×
              </button>
            </span>
          );
        })}
        <input
          className="TagInput"
          type="text"
          placeholder="Press enter to add tags"
          tabIndex={2}
          onChange={(e) => setTagItem(e.target.value)}
          value={tagItem}
          onKeyPress={onKeyPress}
        />
      </div>
    </div>
  );
};

export default TagComponent;
