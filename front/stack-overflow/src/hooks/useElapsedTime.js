import { useState, useEffect } from "react";

const useElapsedTime = (date) => {
  const [elapsedTimeString, setElapsedTimeString] = useState();

  useEffect(() => {
    const currentTime = new Date();
    const createdTime = new Date(date);

    let diffTime = Math.floor(
      (currentTime.getTime() - createdTime.getTime()) / 1000
    );

    console.log(diffTime);

    if (diffTime < 60) setElapsedTimeString(`${Math.floor(diffTime)} secs`);
    else diffTime = diffTime / 60;

    if (diffTime < 60) setElapsedTimeString(`${Math.floor(diffTime)} mins`);
    else diffTime = diffTime / 60;

    if (diffTime < 24) setElapsedTimeString(`${Math.floor(diffTime)} hours`);
    else setElapsedTimeString(`${Math.floor(diffTime / 24)} days`);
  }, [date]);

  return { elapsedTimeString };
};

export default useElapsedTime;
