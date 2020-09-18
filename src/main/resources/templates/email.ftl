<#import "parts/common.ftl" as c>
<@c.page>
    <div>Список  </div>


    <table class="raz" border="1">
        <caption>Таблица поставщика  https://yavshoke.ua/</caption>

        <style type="text/css">
            table {
                background: #ffffff; /* Цвет фона таблицы */
                color: #000000; /* Цвет текста */
                font-size: 12px;
            }
            td b {
                background: #a0dfe0; /* Цвет фона ячеек */
            }
            td span {
                background: #fefffa; /* Цвет фона ячеек */
            }
            td i {
                background: #ffeb05; /* Цвет фона ячеек */
                font-size: 15px;
            }


            td strong {
                font-size: 10px;
                background: #afe5de; /* Цвет фона ячеек */
            }
            * {
                padding: 0;
                margin: 0;
                box-sizing: border-box;
            }


            .raz {
                overflow: auto;  /* добавить полосу прокрутки */
                height: 10em;
                border: 1px solid red;

            }

            .raz th {
                position: sticky;
                top: 0;
                background: lightpink;

            .sticky {
                position: sticky;
                top: 0;
                min-height: 2em;
                background: #fffbf9;
            }


            /*        .table-fixed-head {
                        width: 100%;
                        table-layout: fixed;
                        border-collapse: collapse;
                        height: 100vh;
                    }

                    .table-fixed-head th,
                    .table-fixed-head td {
                        padding: 5px;
                        text-align: center;
                        width: 40.333vw;
                    }
                    .table-fixed-head thead tr {
                        display: block;
                        position: relative;
                    }

                    .table-fixed-head tbody {
                        display: block;
                        overflow: auto;
                        width: 100%;
                        height: calc(100vh - 30px);
                    }

                    .table-fixed-head thead {
                        background-color: #333;
                        color: #FDFDFD;
                    }


                    .table-fixed-head tbody tr:nth-child(even) {
                        background-color: #DDD;
                    }
        */
        </style>
        <thead>
        <tr >
            <th>Код товара</th>
            <th>Наименование товара</th>
            <th>Цена</th>

        </tr>
        </thead>
        <tbody>
        <#if offers??>
        <#list offers as offer>
            <tr class="cells">
                <td><b><#if offer.id_offer??>${offer.id_offer}<#else>"missing"</#if></b></td>
                <td><i>${offer.name}></i></td>
                <td><span>${offer.price}></span></td>
            </tr>
        </#list>
            <#else>
            <td><b>no date</b></td>
            <td><b>no date</b></td>
            <td><b>no date</b></td>
        </#if>
        </tbody>
    </table>
</@c.page>