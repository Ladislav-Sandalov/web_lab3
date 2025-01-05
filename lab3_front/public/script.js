document.addEventListener('DOMContentLoaded', () => {
    const questionsListDiv = document.getElementById('questions-list');
     const addQuestionInput = document.getElementById('question-input');
     const addQuestionBtn = document.getElementById('add-question-btn');
    const randomQuestionBtn = document.getElementById('random-question-btn');
  const randomQuestionText = document.getElementById('random-question-text');

   function fetchQuestions() {
         fetch('http://localhost:8080/questions')
             .then(response => response.json())
             .then(questions => {
                questionsListDiv.innerHTML = '';
                  questions.forEach(question => {
                       const questionItem = document.createElement('div');
                        questionItem.className = 'question-item';
                        questionItem.innerHTML = `
                            <p>${question.text}</p>
                             <button data-id="${question.id}" class="delete-btn">Удалить</button>
                        `;
                        questionsListDiv.appendChild(questionItem);
                    });
                })
             .catch(error => console.error('Ошибка при загрузке вопросов:', error));
  }

    function deleteQuestion(id){
       fetch(`http://localhost:8080/questions/${id}`, {
            method: 'DELETE'
       }).then(() => fetchQuestions())
          .catch(error => console.error('Ошибка при удалении вопроса', error));
    }
     function addQuestion(){
       const questionText = addQuestionInput.value;
          fetch('http://localhost:8080/questions', {
              method: 'POST',
              headers: {
                 'Content-Type': 'application/json'
                },
             body: JSON.stringify(questionText)
          })
          .then(response => response.json())
            .then(() => {
                addQuestionInput.value = '';
              fetchQuestions();
          })
           .catch(error => console.error('Ошибка при добавлении вопроса:', error));
      }
    function getRandomQuestion() {
          fetch('http://localhost:8080/questions/random')
             .then(response => response.json())
             .then(question => {
                 randomQuestionText.textContent = question ? question.text : "Нет вопросов.";
            })
             .catch(error => console.error('Ошибка при загрузке случайного вопроса:', error));
     }


 questionsListDiv.addEventListener('click', event => {
        if (event.target.classList.contains('delete-btn')) {
            const questionId = event.target.getAttribute('data-id');
             deleteQuestion(questionId);
         }
      });
      addQuestionBtn.addEventListener('click', addQuestion);
      randomQuestionBtn.addEventListener('click', getRandomQuestion);
       fetchQuestions();
});