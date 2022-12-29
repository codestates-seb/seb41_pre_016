import Pagination from 'react-js-pagination';
import styled from 'styled-components';

function PaginationBar({ page, totalElements, handlePageChange, totalPages }) {
  return (
    <Container>
      <Pagination
        activePage={page}
        itemsCountPerPage={1}
        totalItemsCount={totalElements}
        pageRangeDisplayed={totalPages < 5 ? totalPages : 5}
        onChange={handlePageChange}
        prevPageText={'Prev'}
        nextPageText={'Next'}
        hideFirstLastPages={true}
        hideDisabled={true}
      />
    </Container>
  );
}

export const Container = styled.div`
  .pagination {
    display: flex;
    justify-content: baseline;
    margin: 20px 0;
  }
  ul {
    list-style: none;
    padding: 0;
  }
  ul.pagination li {
    display: inline-block;
    height: 25px;
    border: 1px solid var(--black-100);
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 13px;

    border-radius: 3px;
    margin: 0 2px;
    padding: 0 8px;
    cursor: pointer;
    :hover {
      background-color: var(--black-100);
    }
  }
  ul.pagination li a {
    text-decoration: none;
    color: hsl(210, 8%, 25%);

    font-size: 13px;
  }
  ul.pagination li.active a {
    color: white;
  }
  ul.pagination li.active {
    background-color: var(--theme-primary-color);
  }
`;

export default PaginationBar;
