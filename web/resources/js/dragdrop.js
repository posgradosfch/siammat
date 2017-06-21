    function initDND() {
        $('.ui-treenode-leaf').draggable({
           helper: 'clone',
           scope: 'treetotable',
           zIndex: ++PrimeFaces.zindex
        });
 
        $('.ui-datatable .droppoint').droppable({
           activeClass: 'ui-state-active',
           hoverClass: 'ui-state-highlight',
           tolerance: 'pointer',
           scope: 'treetotable',
           drop: function(event, ui) {
               var property = ui.draggable.find('.ui-treenode-label').text(),
               droppedColumnId = $(this).parents('th:first').attr('id'),
               dropPos = $(this).hasClass('dropleft') ? 0 : 1;
 
               treeToTable([
                    {name: 'property', value:  property}
                   ,{name: 'droppedColumnId', value: droppedColumnId}
                   ,{name: 'dropPos', value: dropPos}
               ]);
           }
        });
 
        $('.ui-datatable th').draggable({
           scope: 'tabletotree',
           helper: function() {
               var th = $(this);
 
               return th.clone().appendTo(document.body).css('width', th.width());
           }
        });
 
        $('.ui-tree').droppable({
           helper: 'clone',
           scope: 'tabletotree',
           activeClass: 'ui-state-active',
           hoverClass: 'ui-state-highlight',
           tolerance: 'touch',
           drop: function(event, ui) {                               
               tableToTree([
                   {name: 'colIndex', value:  ui.draggable.index()}
               ]);
           }
        });
    }
 
    $(function() {
        initDND();
    });