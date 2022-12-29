import Tags from '../Tags/index';
import styled from 'styled-components';

const TagListDiv = styled.div`
  display: grid;
  grid-template-rows: repeat(5, 200px);
  grid-template-columns: repeat(4, 242px);
  row-gap: 15px;
  column-gap: 15px;
`;

const TagList = ({ tagData }) => {
  return (
    <TagListDiv>
      {tagData && tagData.tags.map((el) => <Tags key={el.tagID} list={el} />)}
    </TagListDiv>
  );
};

export default TagList;
