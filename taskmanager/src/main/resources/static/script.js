$(document).ready(function () {
  loadTasks();

  // Add Task
  $('#taskForm').submit(function (e) {
    e.preventDefault();

    const task = {
      title: $('#title').val(),
      description: $('#description').val(),
      priority: $('#priority').val(),
      deadline: $('#deadline').val()
    };

    $.ajax({
      url: '/api/tasks',
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(task),
      success: function () {
        alert('Task added!');
        $('#taskForm')[0].reset();
        loadTasks();
      }
    });
  });

  // Load all tasks
  function loadTasks() {
    $.get('/api/tasks', function (tasks) {
      let rows = '';
      tasks.forEach(task => {
        rows += `
          <tr>
            <td>${task.id}</td>
            <td>${task.title}</td>
            <td><span class="badge bg-${getPriorityColor(task.priority)}">${task.priority}</span></td>
            <td><span class="badge bg-${getStatusColor(task.status)}">${task.status}</span></td>
            <td>${task.deadline}</td>
            <td>
              <button class="btn btn-sm btn-warning btn-status" onclick="updateStatus(${task.id}, 'IN_PROGRESS')">Start</button>
              <button class="btn btn-sm btn-success btn-status" onclick="updateStatus(${task.id}, 'DONE')">Done</button>
              <button class="btn btn-sm btn-danger btn-status" onclick="deleteTask(${task.id})">Delete</button>
            </td>
          </tr>
        `;
      });
      $('#taskTableBody').html(rows);
    });
  }

  // Change Task Status
  window.updateStatus = function (id, status) {
    $.ajax({
      url: `/api/tasks/${id}/status?status=${status}`,
      type: 'PATCH',
      success: function () {
        loadTasks();
      }
    });
  };

  // Delete Task
  window.deleteTask = function (id) {
    if (confirm('Are you sure you want to delete this task?')) {
      $.ajax({
        url: `/api/tasks/${id}`,
        type: 'DELETE',
        success: function () {
          loadTasks();
        }
      });
    }
  };

  // Color helpers
  function getPriorityColor(priority) {
    return {
      HIGH: 'danger',
      MEDIUM: 'warning',
      LOW: 'success'
    }[priority] || 'secondary';
  }

  function getStatusColor(status) {
    return {
      TODO: 'secondary',
      IN_PROGRESS: 'info',
      DONE: 'success'
    }[status] || 'dark';
  }
});
