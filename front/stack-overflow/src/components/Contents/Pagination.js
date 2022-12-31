import styled from "styled-components";
import PageButton from "../Buttons/PageButton";
import usePagelist from "../../hooks/usePagelist";

const Container = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
  padding-top: 50px;
  margin: 20px 0;
`;

const Pagination = ({ pageInfo, pageHandle }) => {
  const { pageList } = usePagelist(pageInfo?.page, pageInfo?.totalPages);

  return (
    <Container>
      <PageButton number="Prev" />
      {pageList?.map((el, idx) => {
        return pageInfo.page === el ? (
          <PageButton key={idx} number={el} selected />
        ) : (
          <PageButton key={idx} number={el} />
        );
      })}
      <PageButton number="Next" />
    </Container>
  );
};

export default Pagination;
