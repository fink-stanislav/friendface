function switchItem(tabId, paneId, color) {
    var tabs = document.getElementsByClassName('tab');
    for (var i = 0; i < tabs.length; i++) {
        if (tabs[i].id == tabId) {
            tabs[i].style.background = color;
            tabs[i].style.borderBottom = '1px solid #d3d3d3';
        } else {
            tabs[i].style.background = '#d3d3d3';
            tabs[i].style.borderBottom = '1px solid black';
        }
    }
    var panes = document.getElementsByClassName('pane');
    for (var j = 0; j < panes.length; j++) {
        if (panes[j].id == paneId) {
            panes[j].style.display = 'block';
        } else {
            panes[j].style.display = 'none';
        }
    }
}
