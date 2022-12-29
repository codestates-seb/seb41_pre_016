import styled from 'styled-components';

const Button = styled.button`
  display: flex;
  justify-content: center;
  align-items: center;
  color: var(--powder-700);
  font-size: 12px;
  cursor: pointer;
  background-color: var(--powder-100);
  border-radius: 3px;
  padding: 4.8px 6px;
  border: 1px;
  border-color: transparent;
  margin: 2px 6px 2px 0;
  :hover {
    color: var(--powder-800);
    background-color: var(--powder-200);
  }
`;

function Tag({ name }) {
  const handleSearchTag = () => {};

  return <Button onClick={handleSearchTag}>{name}</Button>;
}

export default Tag;
