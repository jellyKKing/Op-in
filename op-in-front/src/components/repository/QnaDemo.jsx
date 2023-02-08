import DefaultImg from "@assets/basicprofile.png";
import Comment from "@components/repository/Comment";
import React, { useState } from "react";
import { userInfo } from "@recoil/user/atoms";
import { useRecoilValue } from "recoil";

export default function QnaDemo({ user_nickname, qna_content, commentlist=[ {user:"A",content:"hello"},{user:"B",content:"hello"},{user:"C",content:"no hello"} ] }) {

  const user = useRecoilValue(userInfo);
  const Comments = commentlist;
  const [text,setText] = useState("");
  
  const createComment = (data) => {
    Comments.push({
      user:user.nickname, 
      content:data,
    });
    console.log(Comments);
  }
  
  const commentRender = (list) => {
    const result = [];
    if(list != null) for(let i = 0; i < list.length; i++){
      result.push(
        <Comment key={i} _text ={list[i].content} _name={list[i].user}/>
      );
    }
    return result;
  }
  return (
    <>
      <div className="w-full p-4 mb-6 bg-white rounded-lg shadow dark:bg-gray-800 sm:inline-block">
        <div className="flex items-start text-left">
          <div className="flex-shrink-0">
            <div className="relative inline-block">
              <a href="#" className="relative block">
                <img
                  alt="profile"
                  src={DefaultImg}
                  className="mx-auto object-cover rounded-full h-16 w-16 "
                />
              </a>
            </div>
          </div>

          <div className="ml-6">
            <p className="flex items-baseline">
              <span className="font-bold text-gray-600 dark:text-gray-200">{user_nickname}</span>
              <span className="ml-2 text-sm text-gray-500 dark:text-gray-300">2 months ago</span>
            </p>
            <div className="mt-3">
              <p className="mt-1 dark:text-white">{qna_content}</p>
            </div>

            <div className="mt-3">
              <label htmlFor="chat" className="sr-only">
                Leave a Comment...
              </label>
              <div className="flex items-center px-3 py-2 rounded-lg bg-gray-50 dark:bg-gray-700">
                <textarea
                  id="chat"
                  rows="1"
                  value={text}
                  onChange={(e)=>{
                    setText(e.target.value) ;
                  }}
                  className="block mx-4 p-2.5 w-full text-sm text-gray-900 bg-white rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-800 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="Leave a Comment..."
                ></textarea>
                <button
                  // type="submit"
                  onClick={() => {
                    createComment(text);
                    setText("");
                  }}
                  className="inline-flex justify-center p-2 text-blue-600 rounded-full cursor-pointer hover:bg-blue-100 dark:text-blue-500 dark:hover:bg-gray-600"
                >
                  <svg
                    aria-hidden="true"
                    className="w-6 h-6 rotate-90"
                    fill="currentColor"
                    viewBox="0 0 20 20"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path d="M10.894 2.553a1 1 0 00-1.788 0l-7 14a1 1 0 001.169 1.409l5-1.429A1 1 0 009 15.571V11a1 1 0 112 0v4.571a1 1 0 00.725.962l5 1.428a1 1 0 001.17-1.408l-7-14z"></path>
                  </svg>
                  <span className="sr-only">Send message</span>
                </button>
              </div>
            </div>

            <hr className="my-4" />
            {/* 댓글 공간 */}
            <div className="grid grid-rows-1 gap-2">
              {commentRender(Comments)}
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
