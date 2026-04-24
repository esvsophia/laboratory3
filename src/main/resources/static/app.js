async function upload() {
    const fileInput = document.getElementById('fileInput');
    if (!fileInput.files[0]) {
        alert("Выберите файл!");
        return;
    }

    const formData = new FormData();
    formData.append('file', fileInput.files[0]);

    try {
        const response = await fetch('/api/missions/upload', {
            method: 'POST',
            body: formData
        });

        if (response.ok) {
            alert("Миссия успешно загружена в архив!");
            loadArchive();
        } else {
            alert("Ошибка при загрузке");
        }
    } catch (error) {
        console.error("Ошибка:", error);
    }
}

async function loadArchive() {
    try {
        const response = await fetch('/api/missions');
        const missions = await response.json();
        const div = document.getElementById('archive');

        div.innerHTML = missions.map(m => `
            <div class="mission-card">
                <div class="mission-header">${m.missionId}</div>
                <div class="mission-info">
                    <b>Дата:</b> ${m.date || 'Не указана'}<br>
                    <b>Локация:</b> ${m.location}<br>
                    <b>Результат:</b> ${m.outcome}
                </div>
                <div class="card-actions">
                    <button class="btn-report" onclick="downloadReport(${m.id})">Отчет</button>
                    <button class="btn-delete" onclick="deleteMission(${m.id})">Del</button>
                </div>
            </div>
        `).join('');
    } catch (error) {
        console.error("Ошибка загрузки архива:", error);
    }
}

function downloadReport(id) {
    const type = document.getElementById('reportType').value;
    window.location.href = `/api/reports/${id}?type=${type}`;
}

async function deleteMission(id) {
    if (confirm("Вы уверены, что хотите удалить запись из архива?")) {
        await fetch(`/api/missions/${id}`, { method: 'DELETE' });
        loadArchive();
    }
}

loadArchive();