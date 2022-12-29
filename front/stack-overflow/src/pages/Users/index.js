import axios from 'axios';
import { useEffect, useState } from 'react';
import { AiOutlineSearch } from 'react-icons/ai';
import UserCard from '../../components/UserCard';
import styled from 'styled-components';

const URL = process.env.REACT_APP_API_URL;

const Container = styled.div`
  padding: 24px;
  > h1 {
    font-size: 27px;
  }
`;

const FilterBlock = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin: 30px 0px;
  > div#input {
    display: flex;
    border: 1px rgb(159, 166, 173) solid;
    border-radius: 5px;
    height: 40px;
    width: 188px;

    align-items: center;
    padding: 5px;
    > input {
      font-size: 13px;
      border: none;
      color: 1px rgb(144, 151, 159) solid;
      padding-left: 5px;
    }
  }
`;
const Buttons = styled.div`
  > .btn {
    background-color: white;
    border: 1px rgb(159, 166, 173) solid;
    font-weight: 400;
    color: rgb(159, 166, 173);
    font-size: 13px;
    flex-wrap: nowrap;
    height: 40px;
    width: auto;
    margin: 0;

    &.active {
      background-color: rgb(221, 224, 227);
      color: rgb(60, 65, 69);
      font-weight: 200;
    }
  }
  > #left-radius {
    border-radius: 5px 0px 0px 5px;
  }
  > #right-radius {
    border-radius: 0px 5px 5px 0px;
  }
`;

const TextBlock = styled.div`
  padding: 10px 40px;
  > h2 {
    font-size: 21px;
    font-weight: 400;
    padding: 3px;
  }
  > p {
    font-size: 15px;
    padding: 3px;
    a {
      text-decoration: none;
      color: rgb(0, 116, 204);
    }
  }
`;

const UserListBlock = styled.div`
  width: 100%;
  display: flex;
  flex-wrap: wrap;
`;

const Users = () => {
  const [users, setUsers] = useState([]);
  const name = ['Reputation', 'New users', 'Voters', 'Editors', 'Moderators'];
  const [btnActive, setBtnActive] = useState(4);
  const [order, setOrder] = useState('');

  useEffect(() => {
    axios
      .get(
        btnActive === 0 || btnActive === 2
          ? `${URL}/user?order=${order}`
          : `${URL}/user`,
        {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
          },
        }
      )
      .then((res) => setUsers(res.data.users))
      .catch((error) => console.log(error));
  }, []);

  const handleClick = (e) => {
    setBtnActive(e.target.value);
    setOrder(name[e.target.value].toLowerCase());

    axios
      .get(
        btnActive === 0 || btnActive === 2
          ? `${URL}/users?order=${order}`
          : `${URL}/users`,
        {
          headers: {
            'Content-Type': 'application/json;charset=UTF-8',
          },
        }
      )
      .then((res) => {
        setUsers(res.data.users);
      })
      .catch((error) => console.log(error));
  };

  return (
    <Container>
      <h1>Users</h1>
      <FilterBlock>
        <div id="input">
          <AiOutlineSearch color="rgb(159, 166, 173)" />
          <input placeholder="Filter by user"></input>
        </div>
        <Buttons>
          {name.map((ele, index) => (
            <button
              onClick={handleClick}
              key={ele}
              value={index}
              className={'btn' + (index === Number(btnActive) ? ' active' : '')}
              id={
                (index === 0 ? 'left-radius' : '') ||
                (index === 4 ? 'right-radius' : '')
              }
            >
              {ele}
            </button>
          ))}
        </Buttons>
      </FilterBlock>

      <UserListBlock>
        {users.map((ele) => (
          <div key={ele.userId}>
            <UserCard
              displayName={ele.displayName}
              answerCount={ele.answerCount}
              userId={ele.userId}
              tags={ele.tags}
            />
          </div>
        ))}
      </UserListBlock>
    </Container>
  );
};

export default Users;
