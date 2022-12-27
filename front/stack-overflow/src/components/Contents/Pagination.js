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
  console.log(pageList);
  // 1. 현재페이지가 2 이상인경우에는 Prev 버튼 노출
  // 2. 현재페이지가 5 이상인경우에는 1번 버튼 & 중간리스트 앞에 ... 노출
  // 3. 현재페이지가 5 이상인경우에는 현재페이지버튼이 중앙으로 가도록 설정
  // 4. 현재페이지가 총페이지 - 4 이상인경우 끝단 ... 제거

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
